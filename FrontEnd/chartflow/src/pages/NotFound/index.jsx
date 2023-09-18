import useCustomNavigate from "../../hooks/useCustomNavigate";

function NotFound() {
  const { handleMainNavigate } = useCustomNavigate();

  return (
    <>
      <div>접근이 불가능한 페이지입니다.</div>
      <div>
        <button onClick={handleMainNavigate}>메인으로 돌아가기</button>
      </div>
    </>
  );
}

export default NotFound;
