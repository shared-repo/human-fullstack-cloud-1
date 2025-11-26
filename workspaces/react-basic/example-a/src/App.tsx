import './App.css'

function App() {

  const name:string = "John Doe"
  const active:boolean = true;
  const style = {
    color: "red"
  }

  return (
    // 아래는 JSX 구문 영역입니다.
    <> {/* 최상위 요소를 만들기 위한 임시 태그입니다. */}
      // JSX 태그 영역 안에서 이 줄은 주석이 아닙니다.
      <h1 className='blue-text'>Hello, React World !!!</h1>
      <h1 style={style}>처음 만드는 리액트 컴포넌트</h1>
      <input type="text" tabIndex={1} />
      <h1>Hello, { name }</h1>
      <h1>{ active ? "활성사용자" : "비활성사용자" }</h1>
      <div style={ { fontSize:20, fontWeight:"bold", color:"red" } }>스타일 적용</div>
      <div 
        // 여기서 javascript 주석은?
      >주석 특이 사항</div>
    </>
  )
}

export default App
