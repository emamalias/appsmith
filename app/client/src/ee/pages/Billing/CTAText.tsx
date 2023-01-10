import { Icon, Text, TextType } from "design-system";
import React from "react";
import { CTATextWrapper } from "./styles";
import { CTATextProps } from "./types";

export function CtaText(props: CTATextProps) {
  const { action, icon, text } = props;
  return (
    <CTATextWrapper>
      <Text
        as={"button"}
        className="cta-text"
        onClick={action}
        type={TextType.P2}
        weight="600"
      >
        {text}
      </Text>
      {icon && <Icon {...icon} className="cta-icon" />}
    </CTATextWrapper>
  );
}
