export default class Member {
    constructor(
        public fname: string,
        public lname: string,
        public id: number,
        public email: string,
        public groups: number[]
        ) {}
}