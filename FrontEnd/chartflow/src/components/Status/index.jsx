import styles from "./Status.module.css";

function Status() {
  const Title = () => <div className={styles.title}>게임현황</div>;
  const Line = () => <div className={styles.line}></div>;
  const MyAsset = () => (
    <div className={styles.myAsset}>
      <div className={styles.assetText}>총 평가자산</div>
      <div className={styles.assetNum}>10,000,000원</div>
      <div className={styles.assetText}>-</div>
    </div>
  );
  const Detail = () => (
    <div className={styles.container3}>
      <div className={styles.init}>초기자산</div>
      <div className={styles.initNum}>10,000,000원</div>
      <div className={styles.cash}>보유현금</div>
      <div className={styles.cashNum}>10,000,000</div>
      <div className={styles.buystock}>주식매입금</div>
      <div className={styles.buystockNum}>-</div>
      <div className={styles.stockValue}>주식평가금</div>
      <div className={styles.stockValueNum}>-</div>
      <div className={styles.stocks}>주식수</div>
      <div className={styles.stocksNum}>-</div>
      <div className={styles.avgPrice}>평단가</div>
      <div className={styles.avgPriceNum}>-</div>
      <div className={styles.curPrice}>현재가</div>
      <div className={styles.curPriceNum}>-</div>
    </div>
  );

  return (
    <div className={styles.container2}>
      <Title />
      <Line />
      <MyAsset />
      <Detail />
    </div>
  );
}

export default Status;
