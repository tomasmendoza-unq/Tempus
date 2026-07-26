import { HeaderCursadas } from "../../../components/Perfil/HeaderCursadas"

export const ActionList = ({
	items,
	headerTitle,
	emptyMessage,
	renderItem,
}) => (
	<section>
		<HeaderCursadas title={headerTitle} />

		{items.length ? (
			items.map(renderItem)
		) : (
			<div className="py-10 text-center text-gray-400 italic text-sm">
				{emptyMessage}
			</div>
		)}
	</section>
)
