package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionValue3 extends OptionValue {
  public OptionValue3(ASTStringNode string_literal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("string_literal", string_literal)
    }, firstToken, lastToken);
  }
  public OptionValue3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionValue3(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getString_literal() {
    return ((PropertyOne<ASTStringNode>)getProperty("string_literal")).getValue();
  }
}
