import { useState } from 'react';
import styles from './Order.module.css'

function Order() {
    const originStocks = 100
    const [availstocks,setAvailstocks] = useState(originStocks)
    const handleStocks10 = () => {
        setAvailstocks(originStocks*0.1)
    }
    const handleStocks25 = () => {
        setAvailstocks(originStocks*0.25)
    }
    const handleStocks50 = () => {
        setAvailstocks(originStocks*0.5)
    }
    const handleStocks100 = () => {
        setAvailstocks(originStocks*1)
    }
    return (
        <div className={styles.container2}>
            <div className={styles.quantity}>주문수량</div>
            <div className={styles.available}>주문가능</div>
            <div className={styles.availquant}>{originStocks}주</div>
            <input type='number' value={availstocks} className={styles.input}/>
            <button className={styles.btn10} onClick={handleStocks10}>10%</button>
            <button className={styles.btn25} onClick={handleStocks25}>25%</button>
            <button className={styles.btn50} onClick={handleStocks50}>50%</button>
            <button className={styles.btn100} onClick={handleStocks100}>100%</button>
        </div>
    )
}

export default Order;