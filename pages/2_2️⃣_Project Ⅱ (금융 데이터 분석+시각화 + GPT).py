import streamlit as st
import numpy as np
import re
import os
import matplotlib.pyplot as plt
import FinanceDataReader as fdr
from mpl_finance import candlestick_ohlc
import matplotlib.dates as mdates
from datetime import datetime as dt
import datetime
import OpenDartReader
import openai
from langchain.chat_models import ChatOpenAI
from langchain.chains import LLMChain
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.embeddings.openai import OpenAIEmbeddings
from langchain.vectorstores.faiss import FAISS
from langchain.prompts.chat import (
    ChatPromptTemplate,
    SystemMessagePromptTemplate,
    HumanMessagePromptTemplate,
)

def load_data(ticker, start_date,end_date):
    df = fdr.DataReader(ticker,start_date,end_date)
    return df


def plot_graph(stock_code,start_date,end_date,quant):
    df = load_data(stock_code, start_date,end_date)
    today = dt.today().strftime("%Y-%m-%d")

    if quant == '삼중창 (EMA)':
        df = df.dropna()
        ema60 = df.Close.ewm(span=60).mean()
        ema130 = df.Close.ewm(span=130).mean()
        macd = ema60 - ema130
        signal = macd.ewm(span=45).mean()
        macdhist = macd - signal
        df = df.assign(ema130=ema130, ema60=ema60, macd=macd, signal=signal, macdhist=macdhist).dropna()

        df['number'] = df.index.map(mdates.date2num)
        ohlc = df[['number', 'Open', 'High', 'Low', 'Close']]

        ndays_high = df.High.rolling(window=14, min_periods=1).max()
        ndays_low = df.Low.rolling(window=14, min_periods=1).min()

        fast_k = (df.Close - ndays_low) / (ndays_high - ndays_low) * 100
        slow_d = fast_k.rolling(window=3).mean()
        df = df.assign(fast_k=fast_k, slow_d=slow_d).dropna()

        plt.figure(figsize=(9, 9))
        p1 = plt.subplot(3, 1, 1)
        plt.title('Triple Screen Trading')
        plt.grid(True)
        candlestick_ohlc(p1, ohlc.values, width=.6, colorup='red', colordown='blue')
        p1.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))

        p2 = plt.subplot(3, 1, 2)
        plt.grid(True)
        p2.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.plot(df.number, df['ema130'], color='k', label='EMA (130)')
        plt.yticks(color='w')

        buy_list = []
        sell_list = []
        returns = 0
        own_price = 0
        for i in range(1, len(df.Close)):
            if df.ema130.values[i - 1] < df.ema130.values[i] and \
                    df.slow_d.values[i - 1] >= 20 and df.slow_d.values[i] < 20:
                plt.plot(df.number.values[i], df.ema130.values[i], 'r^')
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price == 0:
                    own_price += cost
                    buy_list.append([date, cost])

            elif df.ema130.values[i - 1] > df.ema130.values[i] and \
                    df.slow_d.values[i - 1] <= 80 and df.slow_d.values[i] > 80:
                plt.plot(df.number.values[i], df.ema130.values[i], 'bv')
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
                    sell_list.append([date, cost])
            if i == (len(df.Close) - 1):  # 매수했었는데 마지막 날까지 매도 신호가 안나왔다면 현재의 수익률을 환산
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
        plt.legend(loc='upper left')

        p3 = plt.subplot(3, 1, 3)
        plt.grid(True)
        p3.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.plot(df.number, df['slow_d'], color='g', linestyle='solid', label='%D')
        plt.yticks([0, 20, 80, 100])
        plt.legend(loc='upper left')
        st.pyplot()

    elif quant == '삼중창 (MACD)':
        df = df.dropna()
        ema60 = df.Close.ewm(span=60).mean()
        ema130 = df.Close.ewm(span=130).mean()
        macd = ema60 - ema130
        signal = macd.ewm(span=45).mean()
        macdhist = macd - signal
        df = df.assign(ema130=ema130, ema60=ema60, macd=macd, signal=signal, macdhist=macdhist).dropna()

        df['number'] = df.index.map(mdates.date2num)
        ohlc = df[['number', 'Open', 'High', 'Low', 'Close']]

        ndays_high = df.High.rolling(window=14, min_periods=1).max()
        ndays_low = df.Low.rolling(window=14, min_periods=1).min()

        fast_k = (df.Close - ndays_low) / (ndays_high - ndays_low) * 100
        slow_d = fast_k.rolling(window=3).mean()
        df = df.assign(fast_k=fast_k, slow_d=slow_d).dropna()

        plt.figure(figsize=(9, 9))
        p1 = plt.subplot(3, 1, 1)
        plt.title('Triple Screen Trading')
        plt.grid(True)
        candlestick_ohlc(p1, ohlc.values, width=.6, colorup='red', colordown='blue')
        p1.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))

        p2 = plt.subplot(3, 1, 2)
        plt.grid(True)
        p2.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.bar(df.number, df['macdhist'], color='c', label='MACD-Hist')
        plt.plot(df.number, df['macd'], color='k', label='MACD')
        plt.plot(df.number, df['signal'], 'y:', label='MACD-Signal')
        plt.yticks(color='w')

        buy_list = []
        sell_list = []
        returns = 0
        own_price = 0
        for i in range(1, len(df.Close)):
            if df['macdhist'].values[i - 1] < df['macdhist'].values[i] and \
                    df.slow_d.values[i - 1] >= 20 and df.slow_d.values[i] < 20:
                plt.plot(df.number.values[i], df['macd'].values[i], 'r^')
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price == 0:
                    own_price += cost
                    buy_list.append([date, cost])

            elif df['macdhist'].values[i - 1] > df['macdhist'].values[i] and \
                    df.slow_d.values[i - 1] <= 80 and df.slow_d.values[i] > 80:
                plt.plot(df.number.values[i], df['macd'].values[i], 'bv')
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
                    sell_list.append([date, cost])
            if i == (len(df.Close) - 1):  # 매수했었는데 마지막 날까지 매도 신호가 안나왔다면 현재의 수익률을 환산
                date = np.datetime_as_string(df.index.values[i], unit='D')
                cost = df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
        plt.legend(loc='upper left')

        p3 = plt.subplot(3, 1, 3)
        plt.grid(True)
        p3.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.plot(df.number, df['slow_d'], color='g', linestyle='solid', label='%D')
        plt.yticks([0, 20, 80, 100])
        plt.legend(loc='upper left')
        st.pyplot()

    elif quant == '골든&데드크로스 (5/20)':
        price_df = df.dropna()
        price_df['ema5'] = price_df.Close.ewm(span=5).mean()
        price_df['ema20'] = price_df.Close.ewm(span=20).mean()
        price_df['st_rtn'] = (1 + price_df['Change']).cumprod()
        price_df = price_df.dropna()
        price_df['number'] = price_df.index.map(mdates.date2num)

        ohlc = price_df[['number', 'Open', 'High', 'Low', 'Close']]
        plt.figure(figsize=(9, 9))
        p1 = plt.subplot(2, 1, 1)
        plt.title('Golden Cross & Dead Cross Trade')
        plt.grid(True)
        candlestick_ohlc(p1, ohlc.values, width=.6, colorup='red', colordown='blue')
        p1.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))

        p3 = plt.subplot(2, 1, 2)
        plt.grid(True)
        p3.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.plot(price_df.number, price_df['ema5'], color='g', linestyle='solid', label='EMA 5')
        plt.plot(price_df.number, price_df['ema20'], color='k', linestyle='solid', label='EMA 20')
        plt.yticks(color='w')

        buy_list = []
        sell_list = []
        returns = 0
        own_price = 0
        for i in range(1, len(price_df.Close)):
            if price_df['ema5'].values[i] > price_df['ema20'].values[i] and price_df['ema5'].values[i - 1] < \
                    price_df['ema20'].values[i - 1]:
                plt.plot(price_df.number.values[i], price_df['ema5'].values[i], 'r^')
                date = np.datetime_as_string(price_df.index.values[i], unit='D')
                cost = price_df.Close.values[i]
                if own_price == 0:
                    own_price += cost
                    buy_list.append([date, cost])
            elif price_df['ema5'].values[i] < price_df['ema20'].values[i] and price_df['ema5'].values[i - 1] > \
                    price_df['ema20'].values[i - 1]:
                plt.plot(price_df.number.values[i], price_df['ema5'].values[i], 'bv')
                date = np.datetime_as_string(price_df.index.values[i], unit='D')
                cost = price_df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
                    sell_list.append([date, cost])
            if i == (len(price_df.Close) - 1):  # 매수했었는데 마지막 날까지 매도 신호가 안나왔다면 현재의 수익률을 환산
                cost = price_df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
        plt.legend(loc='upper left')
        st.pyplot()

    elif quant == '골든&데드크로스 (20/60)':
        price_df = df.dropna()
        price_df['ema20'] = price_df.Close.ewm(span=20).mean()
        price_df['ema60'] = price_df.Close.ewm(span=60).mean()
        price_df['st_rtn'] = (1 + price_df['Change']).cumprod()
        price_df = price_df.dropna()
        price_df['number'] = price_df.index.map(mdates.date2num)

        ohlc = price_df[['number', 'Open', 'High', 'Low', 'Close']]
        plt.figure(figsize=(9, 9))
        p1 = plt.subplot(2, 1, 1)
        plt.title('Golden Cross & Dead Cross Trade')
        plt.grid(True)
        candlestick_ohlc(p1, ohlc.values, width=.6, colorup='red', colordown='blue')
        p1.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))

        p3 = plt.subplot(2, 1, 2)
        plt.grid(True)
        p3.xaxis.set_major_formatter(mdates.DateFormatter('%Y-%m'))
        plt.plot(price_df.number, price_df['ema20'], color='g', linestyle='solid', label='EMA 20')
        plt.plot(price_df.number, price_df['ema60'], color='k', linestyle='solid', label='EMA 60')
        plt.yticks(color= 'w')

        buy_list = []
        sell_list = []
        returns = 0
        own_price = 0
        for i in range(1, len(price_df.Close)):
            if price_df['ema20'].values[i] > price_df['ema60'].values[i] and price_df['ema20'].values[i - 1] < \
                    price_df['ema60'].values[i - 1]:
                plt.plot(price_df.number.values[i], price_df['ema20'].values[i], 'r^')
                date = np.datetime_as_string(price_df.index.values[i], unit='D')
                cost = price_df.Close.values[i]
                if own_price == 0:
                    own_price += cost
                    buy_list.append([date, cost])
            elif price_df['ema20'].values[i] < price_df['ema60'].values[i] and price_df['ema20'].values[i - 1] > \
                    price_df['ema60'].values[i - 1]:
                plt.plot(price_df.number.values[i], price_df['ema20'].values[i], 'bv')
                date = np.datetime_as_string(price_df.index.values[i], unit='D')
                cost = price_df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
                    sell_list.append([date, cost])
            if i == (len(price_df.Close) - 1):  # 매수했었는데 마지막 날까지 매도 신호가 안나왔다면 현재의 수익률을 환산
                cost = price_df.Close.values[i]
                if own_price != 0:
                    returns += ((cost / own_price) - 1) * 100
                    own_price = 0
        plt.legend(loc='upper left')
        st.pyplot()


    buy_date_lst , sell_date_lst = [buy[0] for buy in buy_list] , [sell[0] for sell in sell_list]
    buy_price_lst , sell_price_lst =[buy[1] for buy in buy_list] , [sell[1] for sell in sell_list]
    revenues=[round((s-b)/b*100) for b,s in zip(buy_price_lst,sell_price_lst)]
    revenue_lst=[sum(revenues[:i+1]) for i in range(len(revenues))]

    today_price = int(fdr.DataReader(stock_code , end_date)['Close'].values[0])

    st.header('')
    col1, col2, col3 = st.columns(3)
    buy_data = {'날짜' : buy_date_lst , '종가(원)' : buy_price_lst}
    sell_data = {'날짜' : sell_date_lst , '종가(원)' : sell_price_lst}
    
    status='buy'

    # 최적 매수 후, 매도일 발생 X ---> (보유)
    if len(buy_price_lst) > len(sell_price_lst):
        status='buy-hold'
        sell_date_lst.append('보유중...')
        sell_price_lst.append(f'{today_price}')
        last_date_rev = round((today_price-buy_price_lst[-1])/buy_price_lst[-1]*100)
        revenues.append(last_date_rev)

        if len(revenue_lst) > 0:
            last_date_rev_acc = last_date_rev + revenue_lst[-1]
        else:
            last_date_rev_acc = last_date_rev
        
        revenue_lst.append(round(last_date_rev_acc))

    rev_data = {'단순 (%)' : revenues, '누적 (%)' : revenue_lst}

    with col1:
        st.markdown(f"<h4 style='text-align: center; color: white;'>최적 매수</h4>", unsafe_allow_html=True)
        st.table(buy_data)

    with col2:
        st.markdown(f"<h4 style='text-align: center; color: white;'>최적 매도</h4>", unsafe_allow_html=True)
        st.table(sell_data)
            
    with col3:
        st.markdown(f"<h4 style='text-align: center; color: white;'>수익률</h4>", unsafe_allow_html=True)
        st.table(rev_data)

    if status =='buy-hold': # 매수 후 보유
        st.caption(f'(※ {buy_date_lst[-1]} 이후, 매도 시그널이 발생하지 않아 현재가({today_price}원)로 수익률 환산)')
    
    if len(buy_date_lst) == 0:
        st.caption(f'(※ 조회 기간 내, 매수 시그널이 한번도 발생하지 않아 매매하지 않음)')


def check_input(index,input):
    if isinstance(input, str):
        return ''
    elif isinstance(input, (int, float)):
        if input >= 0:
            if index == 'CAGR':
                if input >= 25:
                    return '+ 매우 좋음'
                elif input >= 10 and input < 25:
                    return '+ 좋음'
                elif input > 0 and input < 10:
                    return '+ 적정'
                else:
                    return '- 나쁨'

            elif index =='VOL':
                if input >= 50:
                    return '+ 확대'
                elif input >= 25 and input < 50:
                    return '적정'
                else:
                    return '- 축소'
                
            elif index =='MDD':
                if input >= 75:
                    return '최악'
                elif input >= 50 and input < 75:
                    return '나쁨'
                elif input >= 25 and input < 50:
                    return '- 적정'
                else:
                    return '- 낮음'
                
            elif index =='Sharpe':
                if input >= 0.8:
                    return '+ 최상'
                elif input >= 0.5 and input < 0.8:
                    return '+ 매우 좋음'
                elif input >= 0 and input < 0.5:
                    return '+ 좋음'
                

        elif input < 0:
            if index =='Sharpe':
                if input >= -0.2 and input < 0.2:
                    return '- 주의 필요'
                elif input >= -0.5 and input < -0.2:
                    return '- 나쁨'
                else:
                    return '- 매우 나쁨'

    else:
        return ''


def plot_indicators(stock_code,start_date,end_date):
    
    df = load_data(stock_code, start_date,end_date)
    price_df = df.dropna()

    price_df['st_rtn'] = (1 + price_df['Change']).cumprod()

    historical_max = price_df['Close'].cummax()
    daily_drawdown = price_df['Close'] / historical_max - 1.0
    historical_dd = daily_drawdown.cummin()  ## 최대 낙폭

    MDD = historical_dd.min() * 100 * -1
    simple_return = ((price_df['st_rtn'].values[-1]-1) * 100)
    CAGR = simple_return ** (252. / len(price_df.index)) - 1  # 연평균 복리 수익률
    if str(CAGR) == 'nan':
        CAGR = 0

    VOL = np.std(price_df['Change']) * np.sqrt(252.) * 100
    Sharpe = np.mean(price_df['Change']) / np.std(price_df['Change']) * np.sqrt(252.)


    container = st.container()

    with container:
        st.header('')
        col1, col2 = st.columns([1,2])
        col1.metric(label="연평균 복리 수익률(CAGR)", value=f"{round(CAGR,1)} %", delta=check_input("CAGR",CAGR), delta_color='normal')
        col2.caption('최초 투자한 금액과 투자로부터 발생한 매년 수익을 계속해서')
        col2.caption('재투자할 때, 최초 투자 금액은 최종적으로 복리 개념으로 매년')
        col2.caption('몇 % 수익이 복리로 발생해 왔는가를 환산해 계산한 수치입니다.')

    with container:
        st.title('')
        st.subheader('')
        col1, col2 = st.columns([1,2])
        col1.metric(label="변동성 (VOL)", value = f"{round(VOL,1)} %", delta = check_input("VOL",VOL), delta_color='off')
        col2.caption('조회 기간동안 가격이 움직인 변동폭을 측정해 평균화 했으며,')
        col2.caption('해당 종목의 가격이 상승하거나 하락한 변동폭을 보여줍니다.')
        col2.caption('쉽게 말해 수익 또는 손실이 발생할 확률을 의미하는 수치입니다.')
                
    with container:
        st.title('')
        st.subheader('')
        col1, col2 = st.columns([1,2])
        col1.metric(label="최대낙폭 (MDD)", value=f"{round(MDD,1)} %", delta=check_input("MDD",MDD), delta_color='inverse')
        col2.caption('조회 기간동안 최고점과 최저점을 측정해 비율로 나타냈으며,')
        col2.caption('해당 종목 투자 시 느낄 수 있는 최대 고통을 나타내는 수치입니다.')
        col2.caption('본인의 투자 성향과 관계가 있으며, 스트레스 지수라고도 불립니다.')

    with container:
        st.title('')
        st.subheader('')
        col1, col2 = st.columns([1,2])
        col1.metric(label="샤프지수 (Sharpe)", value=f"{round(Sharpe,1)}", delta = check_input("Sharpe",Sharpe), delta_color='normal')
        col2.caption('위험 대비 초과수익을 얼마나 얻을 수 있는가를 나타내며,')
        col2.caption('위험 대비 수익에 대한 직관적인 비교가 가능한 수치입니다.')
        col2.caption('흔히 샤프지수가 높을수록 투자 성과가 우수하다고 평가됩니다.')


def plot_explain(quant):

    if quant == '삼중창 (EMA)':
        st.subheader('삼중창 매매란?')
        st.text('삼중창 매매법은 추세 추종과 역추세 매매를 함께 사용하며,')
        st.text('3단계의 기준을 거치기 때문에 더욱 정확한 매매시점을 찾을 수 있는 전략입니다.')
        st.header('')

        st.subheader('EMA란?')
        st.text('EMA는 지수 이동평균을 의미하는 인기있는 핵심 지표 중 하나입니다.')
        st.text('지수 이동평균은 최근 데이터일수록 추가 가중치를 부여하기 때문에')
        st.text('단순 이동평균보다 흐름을 더 잘 반영한다는 특징을 가지고 있습니다.')
        st.text('삼중창 매매는 130일 지수 이동평균으로 장기 추세를 판단합니다.')
        st.header('')

        
        st.subheader('구성')
        st.text('첫번째 창 : 추세추종 지표(EMA)를 이용해 추세를 분석')
        st.text('두번째 창 : 오실레이터(스토캐스틱)를 이용해 역추세를 분석')
        st.text('세번째 창 : 앞선 신호를 종합해 매매 시기를 추출 후 판단')
        st.header('')


        st.subheader('장점')
        st.text('1. 다양한 시점(단기/중기/장기)별 매매 신호 비교')
        st.text('2. 추세 추종 & 역추세를 함께 사용해 높은 신뢰성')
        st.header('')

    if quant == '삼중창 (MACD)':
        st.subheader('삼중창 매매란?')
        st.text('삼중창 매매법은 추세 추종과 역추세 매매를 함께 사용하며,')
        st.text('3단계의 기준을 거치기 때문에 더욱 정확한 매매시점을 찾을 수 있는 전략입니다.')
        st.header('')

        st.subheader('MACD 히스토그램?')
        st.text('MACD 히스토그램은 MACD와 신호선을 함께 이용한 지표입니다.')
        st.text('MACD는 12일 지수 이동평균에서 26일 지수 이동평균을 뺀 값을,')
        st.text('신호선은 MACD의 9일 지수 이동평균(EMA) 값을 의미합니다.')
        st.text('이를 통해, 매수/매도의 비중과 추세를 함께 분석할 수 있습니다.')
        st.header('')

        
        st.subheader('구성')
        st.text('첫번째 창 : 추세추종 지표(MACD)를 이용해 추세를 분석')
        st.text('두번째 창 : 오실레이터(스토캐스틱)를 이용해 역추세를 분석')
        st.text('세번째 창 : 앞선 신호를 종합해 매매 시기를 추출 후 판단')
        st.header('')


        st.subheader('장점')
        st.text('1. 다양한 시점(단기/중기/장기)별 매매 신호 비교')
        st.text('2. 추세 추종 & 역추세를 함께 사용해 높은 신뢰성')
        st.header('')

    if quant == '골든&데드크로스 (5/20)':
        st.subheader('골든&데드 크로스란?')
        st.text('두 가지 (단기/장기) 지수 이동평균(EMA)을 활용한 강력한 전략입니다.')
        st.text('골든크로스는 단기선이 장기선을 상향 돌파하는 시점을 의미하고,')
        st.text('데드크로스는 단기선이 장기선을 하향 돌파하는 시점을 의미합니다.')
        st.text('골든크로스는 매수신호를 나타내고 데드크로스는 매도신호를 나타냅니다.')
        st.header('')

        st.subheader('EMA란?')
        st.text('EMA는 지수 이동평균을 의미하는 인기있는 핵심 지표 중 하나입니다.')
        st.text('지수 이동평균은 최근 데이터일수록 추가 가중치를 부여하기 때문에')
        st.text('단순 이동평균보다 흐름을 더 잘 반영한다는 특징을 가지고 있습니다.')
        st.text('지수 이동평균(EMA)은 추세를 분석하기에 매우 유용합니다.')
        st.header('')

        
        st.subheader('매매 판단 기준')
        st.text('매수 : 5일선이 20일선을 상향 돌파')
        st.text('매도 : 20일선이 60일선을 상향 돌파')
        st.header('')


        st.subheader('장점')
        st.text('1. 쉽고 단순하지만 높은 신뢰성')
        st.text('2. 중단기 매매 시 높은 수익률')
        st.header('')

    if quant == '골든&데드크로스 (20/60)':
        st.subheader('골든&데드 크로스란?')
        st.text('두 가지 (단기/장기) 지수 이동평균(EMA)을 활용한 강력한 전략입니다.')
        st.text('골든크로스는 단기선이 장기선을 상향 돌파하는 시점을 의미하고,')
        st.text('데드크로스는 단기선이 장기선을 하향 돌파하는 시점을 의미합니다.')
        st.text('골든크로스는 매수신호를 나타내고 데드크로스는 매도신호를 나타냅니다.')
        st.header('')

        st.subheader('EMA란?')
        st.text('EMA는 지수 이동평균을 의미하는 인기있는 핵심 지표 중 하나입니다.')
        st.text('지수 이동평균은 최근 데이터일수록 추가 가중치를 부여하기 때문에')
        st.text('단순 이동평균보다 흐름을 더 잘 반영한다는 특징을 가지고 있습니다.')
        st.text('지수 이동평균(EMA)은 추세를 분석하기에 매우 유용합니다.')
        st.header('')

        
        st.subheader('매매 판단 기준')
        st.text('매수 : 20일선이 60일선을 상향 돌파')
        st.text('매도 : 60일선이 20일선을 상향 돌파')
        st.header('')


        st.subheader('장점')
        st.text('1. 쉽고 단순하지만 높은 신뢰성')
        st.text('2. 중장기 매매 시 높은 수익률')
        st.header('')


def get_response_from_query(vector_db, query, company, target,k=5):
    """
    gpt-3.5-turbo-16k can handle up to 16k tokens. Setting the chunksize to 1000 and k to 5 maximizes
    the number of tokens to analyze.
    """

    docs = vector_db.similarity_search(query, k=k)
    docs_page_content = " ".join([d.page_content for d in docs])

    chat = ChatOpenAI(model_name="gpt-3.5-turbo-16k", temperature=1)

    # Template to use for the system message prompt
    template = """
        You are a helpful {company} GPT that can ALL answer or explain to {target} about {company} informations.
        Document retrieved from your DB : {docs}

        Answer the questions referring to the documents which you Retrieved from DB as much as possible.
        If you feel like you don't have enough information to answer the question, say "I don't know".

        Since your answer targets {target}, you should return an answer that is optimized for understanding by {target}.
        """

    system_message_prompt = SystemMessagePromptTemplate.from_template(template)

    # Human question prompt
    human_template = "Answer the following question IN KOREAN: {question}"
    human_message_prompt = HumanMessagePromptTemplate.from_template(human_template)

    chat_prompt = ChatPromptTemplate.from_messages(
        [system_message_prompt, human_message_prompt]
    )

    chain = LLMChain(llm=chat, prompt=chat_prompt)

    response = chain.run(question=query, docs=docs_page_content, company=company ,target=target)
    response = response.replace("\n", "")
    return response, docs
    


def extract_refine_text(html_string):
    # Remove CSS styles
    no_css = re.sub('<style.*?</style>', '', html_string, flags=re.DOTALL)
    
    # Remove Inline CSS
    no_inline_css = re.sub('\..*?{.*?}', '', no_css, flags=re.DOTALL)

    # Remove specific undesired strings
    no_undesired = re.sub('\d{4}[A-Za-z0-9_]*" ADELETETABLE="N">', '', no_inline_css)

    # Remove HTML tags
    no_tags = re.sub('<[^>]+>', ' ', no_undesired)

    # Remove special characters and whitespaces
    cleaned = re.sub('\s+', ' ', no_tags).strip()

    # Remove the □ character
    no_square = re.sub('□', '', cleaned)

    # Replace \' with '
    final_text = re.sub(r"\\'", "'", no_square)

    return final_text


def dart_crawling(company_name,start_date,end_date,opendart_api):

    embedding = OpenAIEmbeddings()
    dart = OpenDartReader(opendart_api)
    dart_info_chart = dart.list(company_name,start=start_date,end=end_date)

    #공시이름    #공시번호
    rpt_names , rpt_idxs = dart_info_chart['report_nm'].to_list(), dart_info_chart['rcept_no'].to_list()
    

    docs=[]
    for rpt_idx,rpt_name in zip(rpt_idxs,rpt_names):
        raw_text = dart.document(rpt_idx)
        refine_text = extract_refine_text(raw_text)
        text_splitter = RecursiveCharacterTextSplitter(chunk_size=1000, chunk_overlap=200)
        texts = text_splitter.create_documents([refine_text])
        docs.extend(texts)

    db = FAISS.from_documents(docs, embedding)
    db.save_local(f"DB/vector/Stock")
    print(f"{company_name}({start_date}~{end_date}) VectorDB 구축 완료")




def plot_gpt(stock_name,start_date,end_date,opendart_api,chatgpt_api):
    embedding = OpenAIEmbeddings()
    st.title(f"🤖 {stock_name} 전자공시 GPT")

    # Opendart API 에서 해당 기업의 공시내용 VectorDB 구축
    dart_crawling(stock_name,start_date,end_date,opendart_api)


    # Set OpenAI API key from Streamlit secrets 
    openai.api_key = chatgpt_api

    # Set a default model
    if "openai_model" not in st.session_state:
        st.session_state["openai_model"] = "gpt-3.5-turbo-16k"

    # Initialize chat history
    if "messages" not in st.session_state:
        st.session_state.messages = [{"role": "system", "content": f'안녕하세요! 저는 전자공시를 바탕으로 구성된 {stock_name} GPT 입니다.'}]

    # Display chat messages from history on app rerun
    for message in st.session_state.messages:
        with st.chat_message(message["role"]):
            st.markdown(message["content"])

    # Accept user input
    if prompt := st.chat_input(f"{stock_name} GPT에게 궁금한 내용을 물어보세요!"):

        vector_db = FAISS.load_local(f"DB/vector/Stock",embedding)
        docs = vector_db.similarity_search(prompt, k=5)
        docs_page_content = " ".join([d.page_content for d in docs])


        # Add user message to chat history
        st.session_state.messages.append({"role": "user", "content": prompt})
        st.session_state.messages.append({"role": "system", "content": '참고 문헌 (전자공시 내용):'+ docs_page_content})
        
        # Display user message in chat message container
        with st.chat_message("user"):
            st.markdown(prompt)
        
        # Display assistant response in chat message container
        with st.chat_message("assistant"):
            message_placeholder = st.empty()
            full_response = ""

            for response in openai.ChatCompletion.create(
                    model=st.session_state["openai_model"],
                    messages=[{"role": m["role"], "content": m["content"]} for m in st.session_state.messages],
                    stream=True,
                ):
                full_response += response.choices[0].delta.get("content", "")
                message_placeholder.markdown(full_response + "▌")
            
            message_placeholder.markdown(full_response)
            del st.session_state.messages[-1]
            st.session_state.messages.append({"role": "assistant", "content": full_response})







@st.cache_data
def get_stock_list(market):
    fdr_list = fdr.StockListing(market)
    return fdr_list

def init(): # Web App 설정

    st.set_page_config(
        page_title="SAFFY 금융 데이터 분석 GPT"
    )


def PJT2():
    init()

    st.set_option('deprecation.showPyplotGlobalUse', False)
    st.title("SSAFY PJT II")
    st.subheader(" : 금융 데이터 분석/시각화 + 전자공시 GPT")

    with st.sidebar:
        st.header('사용자 지정 설정')
        st.subheader('')
        col1, col2 = st.columns(2)
        today = datetime.datetime.today()
        with col1:
            st_date = st.date_input("📅조회 시작", today-datetime.timedelta(weeks=4))
            start_date = str(st_date).replace('-','/')    

        with col2:
            ed_date = st.date_input("📅조회 종료", today)
            end_date = str(ed_date).replace('-','/')
        
        st.subheader('')
        quant = st.selectbox("🎯 퀀트 전략", ('삼중창 (EMA)','삼중창 (MACD)','골든&데드크로스 (5/20)','골든&데드크로스 (20/60)'))
        st.subheader('')

        market = st.selectbox("📌 시장 선정", ('KRX 전체','KOSPI 코스피','KOSDAQ 코스닥','KONEX 코넥스'))
        # df_list = fdr.StockListing(market.split(' ')[0])
        df_list = get_stock_list(market.split(' ')[0])
        stock = st.selectbox("📌 종목 선정", (f'{nm}({cd})' for cd,nm in zip(list(df_list['Code']),list(df_list['Name']))))



        st.subheader('')
        st.header('API key 입력')
        st.text('')

        opendart_api = st.text_input('OpenDart API Key:', type='password')
        if opendart_api:
            st.success('API Key 확인 완료!', icon='✅')
        else:
            st.warning('API key를 입력하세요.', icon='⚠️')
        
        chatgpt_api = st.text_input('ChatGPT API Key:', type='password')
        if chatgpt_api:
            st.success('API Key 확인 완료!', icon='✅')
            os.environ["OPENAI_API_KEY"] = chatgpt_api
        else:
            st.warning('API key를 입력하세요.', icon='⚠️')

        if stock:
            stock_name = stock.split('(')[0]
            stock_code = stock.split('(')[-1][:-1]
            with st.spinner('데이터를 불러오는 중입니다.'):
                st.subheader('')
                st.subheader('📋 옵션')
                quant_explain = st.checkbox('📃 퀀트 전략 설명')
                data_visualize = st.checkbox('📈 데이터 시각화')
                data_indicators = st.checkbox('📊 투자 성과 지표')
                opendart_gpt = st.checkbox('🤖 전자공시 GPT')
        st.text('')
        st.text('')
        st.caption('전자공시 GPT 사용 시, 조회 기간에 따라 준비시간이 크게 달라집니다.')
        st.caption(f'이는 {start_date}부터 {end_date}까지 존재하는 {stock_name} 전자공시 내용을')
        st.caption('텍스트 추출한 뒤, VectorDB로 변환하는 과정에 소요되는 시간을 의미합니다.')

        


    st.divider()
    st.title(f"🎯 《{quant} 전략》")
    st.caption('')

    if quant_explain:
        plot_explain(quant)
        st.caption('')

    st.title(f"📌 《{market.split(' ')[0]} - {stock_name}》")
    st.caption('')

    if data_visualize:
        plot_graph(stock_code,start_date,end_date,quant)
        st.caption('')

    if data_indicators:
        st.title('')
        st.header('')
        plot_indicators(stock_code,start_date,end_date)

    if opendart_gpt:
        st.title('')
        st.divider()
        with st.spinner(f"{stock_name} 전자공시 내역을 가져오고 있습니다..."):
            plot_gpt(stock_name,start_date,end_date,opendart_api,chatgpt_api)


# .venv\Scripts\activate.bat
PJT2()


