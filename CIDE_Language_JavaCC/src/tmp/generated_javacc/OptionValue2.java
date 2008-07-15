package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class OptionValue2 extends OptionValue {
  public OptionValue2(BooleanLiteral booleanLiteral, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BooleanLiteral>("booleanLiteral", booleanLiteral)
    }, firstToken, lastToken);
  }
  public OptionValue2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new OptionValue2(cloneProperties(),firstToken,lastToken);
  }
  public BooleanLiteral getBooleanLiteral() {
    return ((PropertyOne<BooleanLiteral>)getProperty("booleanLiteral")).getValue();
  }
}
