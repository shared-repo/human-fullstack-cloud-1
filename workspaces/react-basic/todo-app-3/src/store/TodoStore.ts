import { create } from "zustand";
import { persist } from "zustand/middleware";
import { immer } from "zustand/middleware/immer";

interface TodoStoreState {
    todos: Todo[];
}

interface TodoStoreActions {
    addTodo: (title:string) => void;
    toggleTodoState: (id:number) => void;
    deleteTodo: (id:number) => void;
}

export const useTodoStore = create<TodoStoreState & TodoStoreActions>()( 
    persist( // 자동으로 localstorage에 저장 및 읽기를 지원하는 미들웨어
        immer( // 불변성 지원 : 상태를 직접 변경할 수 있도록 도와주는 미들웨어
            (set) => ({
                todos: [],
                addTodo: (title) => set((state) => { 
                    state.todos.push({ 
                        id: new Date().getTime(), 
                        title: title, 
                        done: false 
                    }); // end of push
                }), // end of set
                toggleTodoState: (id) => set( (state) => {
                    state.todos = state.todos.map( 
                        (todo) => todo.id === id ? { ...todo, done:!todo.done } : todo
                    );
                } ),
                deleteTodo: (id) => set( (state) => { 
                    state.todos = state.todos.filter( (todo) => todo.id !== id );
                })
            })
        ), // end of immer
        { name: "todos" }
    ) // end of persist
);