package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement2 extends Statement {
  public Statement2(BaliTokenDefinition baliTokenDefinition, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BaliTokenDefinition>("baliTokenDefinition", baliTokenDefinition)
    }, firstToken, lastToken);
  }
  public Statement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement2(cloneProperties(),firstToken,lastToken);
  }
  public BaliTokenDefinition getBaliTokenDefinition() {
    return ((PropertyOne<BaliTokenDefinition>)getProperty("baliTokenDefinition")).getValue();
  }
}
