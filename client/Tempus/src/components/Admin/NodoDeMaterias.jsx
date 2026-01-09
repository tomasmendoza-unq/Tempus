import { ReactFlow, ReactFlowProvider } from "@xyflow/react";
import "@xyflow/react/dist/style.css";

const nodes = [
  { id: "alg1", position: { x: 0, y: 0 }, data: { label: "Álgebra I" } },
  { id: "alg2", position: { x: 250, y: 0 }, data: { label: "Álgebra II" } },
  {
    id: "prog1",
    position: { x: 0, y: 150 },
    data: { label: "Programación I" },
  },
];

const edges = [
  { id: "e1", source: "alg1", target: "alg2", label: "correlativa" },
  { id: "e2", source: "prog1", target: "alg2", label: "correlativa" },
];

export default function NodoDeMaterias() {
  return (
    <div style={{ width: "100%", height: "600px" }}>
      <ReactFlowProvider>
        <ReactFlow nodes={nodes} edges={edges} fitView />
      </ReactFlowProvider>
    </div>
  );
}
