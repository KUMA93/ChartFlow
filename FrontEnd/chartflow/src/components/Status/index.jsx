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

  return (
    <div className={styles.container2}>
      <Title />
      <Line />
      <MyAsset />
    </div>
  );
}

export default Status;
