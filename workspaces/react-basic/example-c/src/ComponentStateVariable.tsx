import { useState } from "react"

export default function ComponentStateVariable() {

  // useState: 리액트가 관리하는 상태 변수 만들기 -> 이 변수의 값이 변경되면 리렌더링
  //           값의 변경은 setCount 함수를 사용해야 합니다 ( 직접 변수의 값을 변경할 수 없습니다 )
  const [count, setCount] = useState(0);

  return (
    <>
      <button onClick={ () => {
        // setCount(count + 1)
        setCount( (prevCount) => prevCount + 1 )
        console.log(count)
      }}>증가</button>
      <hr />
      <div>{ count }</div>
    </>    
  )
}