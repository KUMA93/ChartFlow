import styles from "./Articles.module.css";

function Articles() {
  return (
    <>
      <div className={styles.bg}>
        These are Articles.
        <div className={styles.pagination}>pagination</div>
      </div>
    </>
  );
}

export default Articles;
