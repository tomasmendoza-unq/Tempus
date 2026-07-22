import { SelectField } from "../../../shared/components/SelectField/SelectField"
import AuthInput from "../components/AuthInput"

export const fieldRegistry = {
	text: AuthInput,
	email: AuthInput,
	password: AuthInput,
	select: SelectField,
}
