import streamlit as st
import base64
import os
import time
from gtts import gTTS
import graphviz
import datetime
from streamlit_drawable_canvas import st_canvas
from audiorecorder import audiorecorder


def init(): # Web App 설정

    st.set_page_config(
        page_title="SAFFY 금융/경제 지식교육 GPT"
    )



def autoplay_audio(file_path: str):
    with open(file_path, "rb") as f:
        data = f.read()
        b64 = base64.b64encode(data).decode()
        audio_id = st.markdown(
            f"""
            <audio controls autoplay="true" onended="this.remove()">
            <source src="data:audio/mp3;base64,{b64}" type="audio/mp3">
            </audio>
            """,
            unsafe_allow_html=True,
        )
        return audio_id


# List to store messages that have been read
read_messages = []

@st.cache_resource(experimental_allow_widgets=True)
def bot_message(assistant_response):

    global read_messages  # Use the global list
    # Check if the message has already been read
    if assistant_response in read_messages:
        return
    # If not read, add it to the list and mark as read
    read_messages.append(assistant_response)

    message_placeholder = st.empty()
    full_response = ""
    assistant_response = assistant_response
    # Simulate stream of response with milliseconds delay
    for chunk in assistant_response.split():
        full_response += chunk + " "
        time.sleep(0.15)
        # Add a blinking cursor to simulate typing
        message_placeholder.markdown(full_response + "▌")
    message_placeholder.markdown(full_response)

    # Generate Korean audio using gTTS
    kor_wav = gTTS(assistant_response, lang='ko')
    kor_wav.save('tts.mp3')

    audio_id = autoplay_audio('tts.mp3')
    time.sleep(len(assistant_response) / 3.3)
    os.remove('tts.mp3')
    audio_id.empty()



def gpt(user_name,user_date,service_type):
    year = user_date.strftime('%Y')
    month = user_date.strftime('%M')
    day = user_date.strftime('%D')

  
    with st.chat_message("system"):
        bot_message(f'챗봇 상담을 시작합니다.')

    with st.chat_message("assistant"):
        bot_message(f'안녕하세요!')
        bot_message(f'저는 {service_type} 상담 AI 음성봇입니다.')
        bot_message(f'{user_name} 고객님 본인 되십니까?')


    time.sleep(0.5)
    with st.chat_message("user"): # 1. 본인 확인
        chatbot1= st.selectbox("사용자 응답", ('《Ⅰ. 본인 확인 여부》','네','아니오'))
        

    if chatbot1 == '아니오': # 1. 본인 확인 - (아니오)
        with st.chat_message("assistant"):
            bot_message('죄송합니다.')
            bot_message(f'{user_name} 고객님 본인에게만 상담이 가능합니다.')
            bot_message('상담을 종료하겠습니다. 감사합니다.')
        with st.chat_message("system"):
            bot_message(f'챗봇 상담이 종료되었습니다.')

    elif chatbot1 == '네': # 1. 본인 확인 - (네)
        with st.chat_message("assistant"):
            bot_message(f'네! {user_name} 고객님, 반갑습니다!')
            bot_message(f'저희 {service_type} 상품을 가입해주셔서 진심으로 감사드립니다.')
            bot_message('가입하신 상품의 중요한 사항이 제대로 설명되었는지')
            bot_message('확인드리고자 연락드렸습니다')
            bot_message('상담 예상 소요 시간은 5분입니다.')
            bot_message('시간 괜찮으신가요?')

        time.sleep(0.5)
        with st.chat_message("user"): # 2. 통화 가능 여부            
            chatbot2 = st.selectbox("사용자 응답", ('《Ⅱ. 통화 가능 여부》','네','아니오'))

        if chatbot2 == '아니오': # 2. 통화 가능 여부 - (아니오)
            with st.chat_message("assistant"):
                bot_message('네, 알겠습니다. 지금은 상담이 어려우시군요.')
                bot_message('가능한 일정을 선택해 주시면, 상담 일정을 예약해드리겠습니다.')

            col1, col2 = st.columns(2)
            with col1:
                st.subheader('')
                d = st.date_input("예약 날짜", datetime.date(2023, 2, 3))

            with col2:
                st.subheader('')
                t = st.time_input('예약 시간', datetime.time(10, 00))

            st.title('')
            with st.chat_message("assistant"):
                bot_message(f'[ 예약 날짜 : {d} / 예약 시간 : {t} ]')
                bot_message(f'선택하신 일정으로 상담 예약을 확정해드릴까요?')
                

            with st.chat_message('user'):
                chatbot2_ = st.selectbox("사용자 응답", ('《선택하기》','네',))

            if chatbot2_ == '네':
                with st.chat_message("assistant"):
                    bot_message(f'네 알겠습니다!')
                    bot_message(f'선택하신 상담 예약일에 다시 뵙겠습니다.')
                    bot_message(f'상담을 종료하겠습니다.')
                with st.chat_message("assistant"):
                    bot_message(f'챗봇 상담이 종료되었습니다.')

        elif chatbot2 == '네': # 2. 통화 가능 여부 - (네)
            with st.chat_message("assistant"):
                bot_message('고객님, 귀한 시간 내주셔서 감사합니다! ')
                bot_message('지금부터 진행하는 내용은 고객님의 권리 보호를 위해 기록되며,')
                bot_message('답변하신 내용은 향후 민원 발생시,')
                bot_message('중요한 근거자료로 활용되오니,')
                bot_message('정확한 답변 부탁드립니다.')
                
                bot_message(f'계약자와 피보험자가 다른 계약의 경우, {user_name} 고객님의')
                bot_message(f'계약체결에 대한 동의가 반드시 필요합니다.')
                bot_message(f'자필서명이 정확히 이루어지지 않은 경우,')
                bot_message(f'무효계약으로 간주되어 고객님께서 직접적인')
                bot_message(f'불이익 또는 손해를 입으실 수 있습니다.')
                bot_message('고객님께서 청약서에 직접 자필서명을 하셨는지요?')

            time.sleep(0.5)
            with st.chat_message("user"): # 3. 자필 서명 여부            
                chatbot3 = st.selectbox("사용자 응답", ('《Ⅲ. 자필 서명 여부》','네','아니오'))

            if chatbot3 == '아니오': # 3. 자필 서명 여부 - 아니오
                with st.chat_message("assistant"):     
                    bot_message(f'{user_name} 고객님 본인의 서명을 부탁드립니다.')
                    bot_message('서명을 완료하신 후, 하단의 [제출하기] 버튼을 눌러주세요')

                    canvas_result = st_canvas(
                                                fill_color="rgba(255, 165, 0, 0.3)",  # Fixed fill color with some opacity
                                                stroke_width=3,
                                                stroke_color='black',
                                                background_color= 'white',
                                                background_image= None,
                                                update_streamlit= False,
                                                height=150,
                                                width=500,
                                                drawing_mode='freedraw',
                                                point_display_radius=0,
                                                key="canvas",
                                            )
                    
                    if st.button('서명 제출하기'):
                        st.header('')
                        chatbot3 = '네'

                    
            if chatbot3 == '네': # 3. 자필 서명 여부 - 네
                with st.chat_message("assistant"):
                    bot_message('네! 확인 감사합니다. 마지막으로 질문 한 가지 드리겠습니다.')
                    bot_message('직업, 건강상태 등 계약 전 알려야 할 의무 사항을 속이거나')
                    bot_message('제대로 알리지 않아 발생할 수 있는 모든 불이익은 고객님께 귀속됩니다.')
                    bot_message('고객님께서는 해당 내용을 모두 정확히 확인하시고 작성하셨습니까?')

                time.sleep(0.5)
                with st.chat_message("user"): # [4. 내용 인지 확인]            
                    chatbot4 = st.selectbox("사용자 응답", ('《Ⅳ. 내용 인지 확인》','네','아니오'))


                if chatbot4 == '아니오': # 4. 내용 인지 확인 - 아니오
                    with st.chat_message("assistant"):   
                        bot_message('고객님의 정보를 정확히 확인하신 후 상담을 다시 요청해주세요.')
                        bot_message('상담을 종료하겠습니다.')
                    with st.chat_message("system"):   
                        bot_message('상담이 종료되었습니다.')

                elif chatbot4 == '네': # 4. 내용 인지 확인 - 네
                    with st.chat_message("assistant"):   
                        bot_message('소중한 시간 내주셔서 감사드립니다.')
                        bot_message('향후 불편하시거나 궁금하신점 있으시면,')
                        bot_message('담당자나 콜센터로 언제든지 연락주시기 바랍니다.')
                        bot_message('감사합니다.')

def PJT3():
    init()
    
    st.set_option('deprecation.showPyplotGlobalUse', False)
    st.title("Project III")
    st.subheader(" : 금융 시나리오 음성봇")

    with st.sidebar:
        st.header('사용자 정보 입력')
        st.text('')

        
        user_name = st.text_input("😀 사용자 이름")
        st.caption('')

        user_date = st.date_input("📅 상품 가입일")
        st.caption('')

        service_type = st.selectbox("🎯 금융 분야", ('은행','보험','카드','증권'))
        st.caption('')

        chatbot_type= st.selectbox("🎯  서비스 부문", ('완전판매 모니터링',))
        st.caption('')

        st.subheader('📋 옵션')
        flow_visualize = st.checkbox('📊 플로우차트')
        plot_voicegpt = st.checkbox('🔊🤖 시나리오 음성봇 상담')
        

    if flow_visualize:
        st.divider()
        st.header('📊 플로우차트')
        st.header('')

        graph = graphviz.Digraph()
        graph.edge('Ⅰ. 본인확인', 'Ⅰ-⒜ 아니오')
        graph.edge('Ⅰ-⒜ 아니오','상담 종료')
        graph.edge('Ⅰ. 본인확인', 'Ⅰ-⒝ 네')
        graph.edge('Ⅰ-⒝ 네', 'Ⅱ. 통화 가능 여부')
        graph.edge('Ⅱ. 통화 가능 여부', 'Ⅱ-⒜ 아니오')
        graph.edge('Ⅱ-⒜ 아니오', '상담 종료')
        graph.edge('Ⅱ. 통화 가능 여부', 'Ⅱ-⒝ 네')
        graph.edge('Ⅱ-⒝ 네', 'Ⅲ. 자필서명 확인')
        graph.edge('Ⅲ. 자필서명 확인', 'Ⅲ-⒜ 아니오')
        graph.edge('Ⅲ-⒜ 아니오', '서명 입력')
        graph.edge('서명 입력','Ⅲ. 자필서명 확인')
        graph.edge('Ⅲ. 자필서명 확인', 'Ⅲ-⒝ 네')
        graph.edge('Ⅲ-⒝ 네', 'Ⅳ. 내용인지 확인')
        graph.edge('Ⅳ. 내용인지 확인', 'Ⅳ-⒜. 아니오')
        graph.edge('Ⅳ-⒜. 아니오','상담 종료')
        graph.edge('Ⅳ. 내용인지 확인', 'Ⅳ-⒝. 네')
        graph.edge('Ⅳ-⒝. 네', 'Ⅴ. 추가 질문')
        graph.edge('Ⅴ. 추가 질문', 'Ⅴ-⒜ 아니오')
        graph.edge('Ⅴ-⒜ 아니오', '상담 종료')
        graph.edge('Ⅴ. 추가 질문', 'Ⅴ-⒝ 네')
        graph.edge('Ⅴ-⒝ 네', 'Fine-Tune GPT 응답')
        graph.edge('Fine-Tune GPT 응답','Ⅴ. 추가 질문')
        st.graphviz_chart(graph)
        st.subheader('')


    st.divider()
    st.header(f"🔊🤖 {service_type} {chatbot_type} 음성봇")
    st.caption('')


    if plot_voicegpt:
        gpt(user_name,user_date,service_type)



# .venv\Scripts\activate.bat
PJT3()