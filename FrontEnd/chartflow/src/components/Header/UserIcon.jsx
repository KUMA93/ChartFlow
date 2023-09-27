import React, { useEffect, useState } from "react";
import styles from "./Header.module.css";
import { FaUserCircle } from "react-icons/fa";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import jwtDecode from "jwt-decode";

function UserIcon() {
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const { handleMyPageNavigate } = useCustomNavigate();
  const [nickname, setNickname] = useState("");

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  const handleLogout = () => {
    localStorage.clear();
    setDropdownVisible(false);
    window.location.href = "/";
  };

  useEffect(() => {
    let userinfo = jwtDecode(localStorage.getItem("access-token"));
    setNickname(userinfo.username);
  }, []);

  return (
    <div className={styles.userIcon}>
      <div className={styles.userIconContainer} onClick={toggleDropdown}>
        <div className={styles.TextContainer}>
          <div className={styles.getBold}>{nickname}</div>님 환영합니다!
        </div>
        <FaUserCircle size={30} />
      </div>
      {dropdownVisible && (
        <div className={styles.dropdown}>
          <button
            className={styles.dropdownButton}
            onClick={handleMyPageNavigate}
          >
            마이페이지
          </button>
          <button className={styles.dropdownButton} onClick={handleLogout}>
            로그아웃
          </button>
        </div>
      )}
    </div>
  );
}

export default UserIcon;
