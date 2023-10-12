import styles from "./Footer.module.css";

function Footer() {
  return (
    <>
      <div className={styles.background}>
        <div className={styles.footerText}>© 2023 All Rights Reserved.</div>
        <div className={styles.footerText}>
          개발팀: 특화프로젝트 A107 곤듀들
        </div>
        <div className={styles.footerText}>
          대표 김태현 taehyun01212@gmail.com
        </div>
      </div>
    </>
  );
}

export default Footer;
