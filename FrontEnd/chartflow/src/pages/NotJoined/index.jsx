import useCustomNavigate from "../../hooks/useCustomNavigate";

function NotJoined() {
  const { handleLoginNavigate } = useCustomNavigate();
  return (
    <>
      <div>로그인 후 이용가능합니다.</div>
      <div>
        <button onClick={handleLoginNavigate}>로그인하기</button>
      </div>
    </>
  );
}

export default NotJoined;
