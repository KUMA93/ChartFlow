// import { useState } from "react";
import Header from "../../components/Header";
import Toggle from "../../components/Toggle";
import Main from "../../components/Main";

// import styles from "./MainPage.module.css";

const MainPage = () => {
  // const [modalShow, setModalShow] = useState(false);
  // const handleClose = () => setModalShow(false);
  // const handleShow = () => setModalShow(true);

  return (
    <div className="container">
      <Header classname="header" />
      <Main classname="header" />
      <Toggle classname="header" />

      {/* <Login show={modalShow} handleClose={handleClose} /> */}
    </div>
  );
};

export default MainPage;
