import { GroupController } from './Group/Group';
import { MemberController } from './Member/Member';

const groupController = new GroupController();
const memberController = new MemberController();

export {
    groupController,
    memberController
};