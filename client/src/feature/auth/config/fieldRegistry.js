import { SelectField } from "../../../shared/components/SelectField/SelectField"
import AuthInput from "../components/input/AuthInput"

export const fieldRegistry = {
	text: AuthInput,
	email: AuthInput,
	password: AuthInput,
	select: SelectField,
}
