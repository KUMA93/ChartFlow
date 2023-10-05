import s from "./Acquire.module.css"

function Acquire({ isOpen, closeModal}) {

  return (
    <div style={{ display: isOpen ? "block": "none" }}>
      <div className={s.shade}>
        <div className={s.modalTitle}>
          <div>
            칭호 획득 조건
          </div>
          <button onClick={closeModal} className={s.closeBtn}>닫기</button>
        </div>
      </div>
    </div>
  );
}

export default Acquire