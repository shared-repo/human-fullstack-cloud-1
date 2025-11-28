import { useState } from "react"
import TodoEditor from "./components/TodoEditor"
import TodoHeader from "./components/TodoHeader"
import TodoList from "./components/TodoList"

function App() {

  const initData = [
    {id:1, title:'쉬는 시간에 쉬기', done:false},
    {id:2, title:'간식 먹기', done:false},
    {id:3, title:'주말에 전시회 보기', done:false},
  ]
  const [todos, setTodos] = useState<Todo[]>(initData)

  const addTodo = (title:string) => {
    const todo = { id: new Date().getTime(), title: title, done: false }
    //todos.push(todo)
    setTodos([...todos, todo]) // [todos[0], todos[1], ... todos[n-1], todo]
  }

  const toggleTodoState = (id:number) => {
    const tempTodos = todos.map( (todo) => todo.id === id ? { ...todo, done: !todo.done } : todo )
    setTodos(tempTodos)
  }

  return (
    <div className="todo">
      <TodoHeader />
      {/* 할 일 등록 */}
      <TodoEditor addTodo={addTodo} />
      {/* 할 일 목록 */}
      <TodoList todos={todos} toggleTodoState={toggleTodoState} />
      
    </div>   
  )
}

export default App
