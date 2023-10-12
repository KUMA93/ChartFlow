import styles from "./ChartGame.module.css";
import BuySell from "../../components/BuySell";
import Status from "../../components/Status";
import Chart from "../../components/Chart";
import Quit from "../../components/Quit";
import End from "../../components/End";
import Start from "../../components/Start";
import {
  loadGame,
  startGame,
  progressGame,
  getCoin,
} from "../../services/apis/chartgame";
import { useState, useContext } from "react";
import GameContext from "../../context/GameContext";
import loading from "../../assets/images/bean-eater.gif";

const ChartGame = () => {
  const [modalQuitShow, setModalQuitShow] = useState(false);
  const [modalStartShow, setModalStartShow] = useState(true);
  const [data, setData] = useState();

  const {
    gameId,
    setGameId,
    thisTurn,
    setThisTurn,
    setAssetPer,
    setInitNum,
    setCashNum,
    setStocksNum,
    setAvgPriceNum,
    setCoin,
  } = useContext(GameContext);

  const handleModalQuit = () => {
    setModalQuitShow(!modalQuitShow);
  };

  const handleQuitClose = () => {
    setModalQuitShow(false);
  };

  const handleStartNew = () => {
    // if (localStorage.getItem("isSaved")) {
    const requestData = JSON.stringify({
      gameHistoryId: gameId,
      mode: 3,
    });
    localStorage.removeItem("isSaved");
    setModalStartShow(false);
    progressGame(requestData, {
      headers: { "Content-Type": "application/json" },
    })
      .then(
        startGame()
          .then((res) => {
            loadGame()
              .then((res) => {
                setCashNum(res.gameHistory.cashBudget);
                setGameId(res.gameHistory.gameHistoryId);
                setInitNum(res.gameHistory.initialBudget);
                setAvgPriceNum(res.gameHistory.price);
                setStocksNum(res.gameHistory.quantity);
                setAssetPer(res.gameHistory.rate);
                setThisTurn(res.gameHistory.turn);
                setData(res.chartData);
                getCoin()
                  .then((res) => {
                    setCoin(res.assets.coin);
                  })
                  .catch((err) => {
                    console.error(err);
                  });
              })
              .catch((err) => {
                console.error(err);
              });
          })
          .catch((err) => console.log(err))
      )
      .catch((err) => console.log(err));
  };

  const handleContinue = () => {
    localStorage.removeItem("isSaved");
    setModalStartShow(false);
    loadGame()
      .then((res) => {
        setCashNum(res.gameHistory.cashBudget);
        setGameId(res.gameHistory.gameHistoryId);
        setInitNum(res.gameHistory.initialBudget);
        setAvgPriceNum(res.gameHistory.price);
        setStocksNum(res.gameHistory.quantity);
        setAssetPer(res.gameHistory.rate);
        setThisTurn(res.gameHistory.turn);
        setData(res.chartData);
        getCoin().then((res) => {
          setCoin(res);
        });
      })
      .catch((err) => {
        console.error(err);
      });
  };

  return (
    <>
      <div className={styles.container}>
        <div className={styles.chart}>
          {data ? (
            <Chart data={data} thisTurn={thisTurn} />
          ) : (
            <>
              <div className={styles.shade}></div>
              <img src={loading} alt="" className={styles.loading} />
            </>
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
      {modalQuitShow && <Quit handleClose={handleQuitClose} />}
      {modalStartShow && (
        <Start
          handleStartNew={handleStartNew}
          handleContinue={handleContinue}
        />
      )}
      {thisTurn === 51 && <End />}
    </>
  );
};

export default ChartGame;
