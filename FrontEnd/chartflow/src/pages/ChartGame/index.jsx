import styles from "./ChartGame.module.css";

const ChartGame = () => {
  return (
    <div className={styles.container}>
      <div className={styles.chart}>주식 차트</div>
      <div className={styles.buysell}>주식 매매</div>
      <div className={styles.status}>게임 현황</div>
      <div className={styles.hint}>힌트</div>
      <div className={styles.settings}>설정</div>
      <div className={styles.end}>게임 종료</div>
    </div>
  );
};

export default ChartGame;
