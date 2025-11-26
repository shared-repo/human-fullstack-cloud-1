import { useState } from 'react'
import './App.css'
import ComponentStateVariable from './ComponentStateVariable'
import Decrease from './Decrease'
import Increase from './Increase'
import LocalStateVariable from './LocalStateVariable'

function App() {
  // useState: 리액트가 관리하는 상태 변수 만들기 -> 이 변수의 값이 변경되면 리렌더링
  //           값의 변경은 setCount 함수를 사용해야 합니다 ( 직접 변수의 값을 변경할 수 없습니다 )
  const [count, setCount] = useState(0)

  return (
    <>
      <table>
        <tr>
          <td><LocalStateVariable /></td>
          <td><ComponentStateVariable /></td>
        </tr>
      </table>
      <hr />
      <table>
        <tr>
          <td><Increase setCount={setCount} /></td>
          <td><Decrease setCount={setCount} /></td>
        </tr>
        <tr>
          <th colSpan={2}>{ count }</th>
        </tr>
      </table>
    </>
  )
}

export default App
