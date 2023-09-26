import styles from "./ChartGame.module.css";
import BuySell from "../../components/BuySell";
import Status from "../../components/Status";
import Chart from "../../components/Chart";
import Quit from "../../components/Quit";
import End from "../../components/End";
import Start from "../../components/Start";
import useCustomNavigate from "../../hooks/useCustomNavigate";
import {
  loadGame,
  startGame,
  loadGameHistory,
} from "../../services/apis/chartgame";
import { useState, useContext } from "react";
import GameContext from "../../context/GameContext";
import loading from "../../assets/images/bean-eater.gif";

const ChartGame = () => {
  const { handleMainNavigate } = useCustomNavigate();
  const [modalQuitShow, setModalQuitShow] = useState(false);
  const [modalStartShow, setModalStartShow] = useState(true);
  const [modalEndShow, setModalEndShow] = useState(false);
  const [data, setData] = useState();
  const [amount, setAmount] = useState();
  const { gameId, setGameId, thisTurn, setThisTurn } = useContext(GameContext);

  const handleModalQuit = () => {
    modalQuitShow ? setModalQuitShow(false) : setModalQuitShow(true);
  };

  const handleQuitClose = () => {
    setModalQuitShow(false);
  };

  const handleStartClose = () => {
    setModalStartShow(false);
    startGame()
      .then((res) => {
        loadGame()
          .then((res) => {
            setGameId(res.gameHistory.gameHistoryId);
            setThisTurn(res.gameHistory.turn);
            setData(res.chartData);
            // loadGameHistory().then((res) => {
            //   setThisTurn(res.gameHistory.turn);
            //   setGameId(res.gameHistoryId);
            //   setInitNum(res.gameHistory.initialBudget);
            //   setCashNum(res.gameHistory.cashBudget);
            //   setAvgPriceNum(res.gameHistory.price);
            //   setStocksNum(res.gameHistory.quantity);
            //   setAssetPer(res.gameHistory.rate);
            // });
          })
          .catch((err) => {
            console.error(err);
          });
      })
      .catch((err) => console.error(err));
  };

  return (
    <>
      <div className={styles.container}>
        <div className={styles.chart}>
          {data ? (
            <Chart data={data} />
          ) : (
            <img src={loading} alt="" className={styles.loading} />
          )}
        </div>
        <div className={styles.buysell}>
          <BuySell thisTurn={thisTurn} />
        </div>
        <div className={styles.status}>
          <Status />
        </div>
        <button className={styles.hint}>힌트</button>
        <button className={styles.end} onClick={handleModalQuit}>
          게임 종료
        </button>
      </div>
      {modalQuitShow ? (
        handleMainNavigate()
      ) : modalQuitShow ? (
        <Quit handleClose={handleQuitClose} />
      ) : null}
      {modalStartShow ? (
        <Start
          handleStartClose={handleStartClose}
          setModalStartShow={setModalStartShow}
        />
      ) : null}
      {modalEndShow && <End />}
    </>
  );
};

export default ChartGame;
