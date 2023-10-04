import exp from "constants";
import s from "./GameRecord.module.css";
import { deflate } from "zlib";


function GameRecord() {
  return (
    <div className={s.shade}>
      <div className={s.modalRecord}>
      </div>
    </div>
  );
}

export default GameRecord;