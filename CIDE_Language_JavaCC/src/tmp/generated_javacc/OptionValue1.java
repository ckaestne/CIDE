package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionValue1 extends OptionValue {
  public OptionValue1(ASTStringNode integer_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("integer_literal", integer_literal)
    }, firstToken, lastToken);
  }
  public OptionValue1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionValue1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getInteger_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("integer_literal")).getValue();
  }
}
