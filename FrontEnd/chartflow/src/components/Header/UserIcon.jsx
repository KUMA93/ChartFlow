import React, { useState } from "react";
import styles from "./Header.module.css";
import { FaUserCircle } from "react-icons/fa";
import useCustomNavigate from "../../hooks/useCustomNavigate";

function UserIcon({ isLogin, handleIsLogin }) {
  const [dropdownVisible, setDropdownVisible] = useState(false);
  const { handleMyPageNavigate } = useCustomNavigate();

  const toggleDropdown = () => {
    setDropdownVisible(!dropdownVisible);
  };

  const handleLogout = () => {
    localStorage.clear();
    setDropdownVisible(!dropdownVisible);
    handleIsLogin();
  };

  return (
    <div className={styles.userIcon}>
      <div className={styles.userIconContainer} onClick={toggleDropdown}>
        <FaUserCircle size={24} /> 
      </div>
      {dropdownVisible && (
        <div className={styles.dropdown}>
          <button className={styles.dropdownButton} onClick={handleMyPageNavigate}>마이페이지</button>
          <button className={styles.dropdownButton} onClick={handleLogout}>로그아웃</button>
        </div>
      )}
    </div>
  );
}

export default UserIcon;
