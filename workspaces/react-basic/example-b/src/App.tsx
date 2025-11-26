import './App.css'
import ClassComponent from './ClassComponent'
import ClassComponent2 from './ClassComponent2'
import FunctionComponent from './FunctionComponent'
import FunctionComponent2 from './FunctionComponent2'

function App() {

  return (
    <>
      <ClassComponent name="John Doe" email="johndoe@example.com" />
      <hr />
      <ClassComponent2 />
      <hr />
      <FunctionComponent phone="010-6523-7741" age={37} />
      <hr />
      <FunctionComponent2>
        <p>공유</p>
        <p>이동욱</p>
        <p>허남준</p>
      </FunctionComponent2>
    </>
  )
}

export default App
