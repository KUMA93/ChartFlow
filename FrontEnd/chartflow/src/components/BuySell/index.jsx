import styles from './BuySell.module.css'
import Order from '../Order';

function BuySell() {
    return (
        <>
        <div className={styles.container}>
            <div className={styles.turn}>turn</div>
            <div className={styles.order}><Order/></div>
            <button className={styles.buybtn}>매수</button>
            <button className={styles.sellbtn}>매도</button>
            <button className={styles.skip}>건너뛰기</button>
        </div>
        </>
    )
}

export default BuySell;