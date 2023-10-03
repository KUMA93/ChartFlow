import styles from "./Order.module.css";

function Order(props) {
  const handleStocks10 = () => {
    props.setOrderedStocks(Math.floor(props.originStocks * 0.1));
  };
  const handleStocks25 = () => {
    props.setOrderedStocks(Math.floor(props.originStocks * 0.25));
  };
  const handleStocks50 = () => {
    props.setOrderedStocks(Math.floor(props.originStocks * 0.5));
  };
  const handleStocks100 = () => {
    props.setOrderedStocks(props.originStocks * 1);
  };
  function handleChange(e) {
    props.setOrderedStocks(e.target.value);
    if (e.target.value > props.originStocks) {
      alert("최대 주문가능수량을 초과하였습니다.");
      props.setOrderedStocks(props.originStocks);
    } else if (e.target.value <= 0) {
      alert("최소 주문가능수량은 1주입니다.");
      props.setOrderedStocks(1);
    }
  }
  return (
    <div className={styles.container2}>
      <div className={styles.quantity}>주문수량</div>
      <div className={styles.buyable}>매수가능</div>
      <div className={styles.buyNum}>
        {props.originBuyStocks ? props.originBuyStocks : "-"}주
      </div>
      <div className={styles.sellable}>매도가능</div>
      <div className={styles.sellNum}>
        {props.originSellStocks ? props.originSellStocks : "-"}주
      </div>
      <input
        type="number"
        value={props.orderedStocks}
        className={styles.input1}
        onChange={handleChange}
      />
      <button className={styles.btn10} onClick={handleStocks10}>
        10%
      </button>
      <button className={styles.btn25} onClick={handleStocks25}>
        25%
      </button>
      <button className={styles.btn50} onClick={handleStocks50}>
        50%
      </button>
      <button className={styles.btn100} onClick={handleStocks100}>
        100%
      </button>
    </div>
  );
}

export default Order;
