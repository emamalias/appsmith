import type { Meta, StoryObj } from "@storybook/react";
import { TextInput } from "@design-system/headless";

/**
 * TextInput component allows users to input text. It is mostly used in forms.
 */
const meta: Meta<typeof TextInput> = {
  component: TextInput,
  title: "Design-system/headless/TextInput",
  args: {
    label: "Label",
    placeholder: "Placeholder",
  },
};

export default meta;
type Story = StoryObj<typeof TextInput>;

export const Main: Story = {};
