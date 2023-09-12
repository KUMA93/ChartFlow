import { useNavigate } from "react-router-dom";
import main_logo from "./../../assets/images/main_logo.png";
import styles from "./Header.module.css";

function Header() {
  const navigate = useNavigate();
  const MenuItems = () => (
    <ul className={styles.container}>
      <li>
        <a className="navbar-brand" href="/">
          <img
            src={main_logo}
            alt="main_logo"
            width={155}
            style={{ alignSelf: "center" }}
          />
        </a>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/game");
          }}
        >
          차트게임
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/boards");
          }}
        >
          커뮤니티
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/hist");
          }}
        >
          히스토리
        </button>
      </li>
      <li>
        <button
          className={styles.tab}
          onClick={() => {
            navigate("/mypage");
          }}
        >
          마이페이지
        </button>
      </li>
      <li>
        <button className={styles.tab} onClick={() => {}}>
          지금 시작
        </button>
      </li>
    </ul>
  );

  return (
    <div>
      <nav>
        <div>
          <MenuItems />
        </div>
      </nav>
    </div>
  );
}

export default Header;
