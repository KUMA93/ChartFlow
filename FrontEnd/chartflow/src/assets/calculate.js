export default function calculateDaysAgo(registerTime) {
  const currentDate = new Date();
  const registerDate = new Date(registerTime);
  const timeDifference = currentDate - registerDate;
  const secondsAgo = Math.floor(timeDifference / 1000); // 초 단위로 변경

  if (secondsAgo < 60) {
    return `${secondsAgo}초 전`; // 1분 미만일 때 초 단위로 표시
  } else if (secondsAgo < 60 * 60) {
    const minutesAgo = Math.floor(secondsAgo / 60);
    return `${minutesAgo}분 전`; // 1시간 미만일 때 분 단위로 표시
  } else if (secondsAgo < 60 * 60 * 24) {
    const hoursAgo = Math.floor(secondsAgo / (60 * 60));
    return `${hoursAgo}시간 전`; // 1일 미만일 때 시간 단위로 표시
  } else if (secondsAgo < 30 * 60 * 60 * 24) {
    const daysAgo = Math.floor(secondsAgo / (60 * 60 * 24));
    return `${daysAgo}일 전`; // 30일 미만일 때 일 단위로 표시
  } else {
    const monthsAgo = Math.floor(secondsAgo / (30 * 60 * 60 * 24));
    return `${monthsAgo}달 전`; // 그 외에는 월 단위로 표시
  }
}
