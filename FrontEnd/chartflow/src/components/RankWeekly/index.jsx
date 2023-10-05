import React, { useState, useEffect } from 'react';
import styles from "./RankWeekly.module.css";
import medal from "../../assets/images/medal.png";
import { topNranking } from "../../services/apis/ranking";

function RankWeekly() {
  const Line = () => <div className={styles.line}></div>;
  const [members, setMembers] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const data = await topNranking(5); 
      const rankings = Object.entries(data.rankings).map(([username, score], index) => ({
        number: index + 1,
        username,
        score
      }));
      setMembers(rankings);
    };

    fetchData();
  }, []);

  return (
    <>
      <div className={styles.bg}>
        <div className={styles.innerText}>
          <div className={styles.title}>
            <img src={medal} alt="" className={styles.medalImg} />
            <div className={styles.titleName}>랭킹 TOP 5</div>
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
