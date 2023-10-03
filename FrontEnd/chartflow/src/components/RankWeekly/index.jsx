import styles from "./RankWeekly.module.css";
import medal from "../../assets/images/medal.png";

function RankWeekly() {
  const Line = () => <div className={styles.line}></div>;
  const members = [
    { number: 1, username: "name" },
    { number: 2, username: "name" },
    { number: 3, username: "name" },
    { number: 4, username: "name" },
    { number: 5, username: "name" },
  ];

  return (
    <>
      <div className={styles.bg}>
        <div className={styles.innerText}>
          <div className={styles.title}>
            <img src={medal} alt="" className={styles.medalImg} />
            <div className={styles.titleName}>이번주 랭킹 TOP 5</div>
          </div>
          <Line />
          <div className={styles.list}>
            {members.map((member) => (
              <div key={member.number} className={styles.member}>
                <div className={styles.number}>{member.number}</div>
                <div className={styles.profilePic}></div>
                <div className={styles.username}>{member.username}</div>
              </div>
            ))}
          </div>
        </div>
      </div>
    </>
  );
}

export default RankWeekly;
