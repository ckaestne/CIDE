package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ComplexRegexNode extends AngleRegex {
  public ComplexRegexNode(Label label, ComplexRegex complexRegex, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<Label>("label", label),
      new PropertyOne<ComplexRegex>("complexRegex", complexRegex)
    }, firstToken, lastToken);
  }
  public ComplexRegexNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ComplexRegexNode(cloneProperties(),firstToken,lastToken);
  }
  public Label getLabel() {
    return ((PropertyZeroOrOne<Label>)getProperty("label")).getValue();
  }
  public ComplexRegex getComplexRegex() {
    return ((PropertyOne<ComplexRegex>)getProperty("complexRegex")).getValue();
  }
}
