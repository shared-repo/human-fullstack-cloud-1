
import './App.css'
import style from './App.module.css'

import StyledComponent1 from './component/StyledComponent1'
import StyledComponent2 from './component/StyledComponent2'
import StyledComponent3 from './component/StyledComponent3'

function App() {
  
  return (
    <>
      <StyledComponent1 />
      <StyledComponent2 />
      <button className="btn">부모 컴포넌트의 버튼</button>
      <StyledComponent3 />
      <br />
      <button className={style.btn2}>부모 컴포넌트의 버튼 2</button>
    </>
  )
}

export default App
