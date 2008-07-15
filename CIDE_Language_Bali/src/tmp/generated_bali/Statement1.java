package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Statement1 extends Statement {
  public Statement1(BaliGrammarRule baliGrammarRule, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BaliGrammarRule>("baliGrammarRule", baliGrammarRule)
    }, firstToken, lastToken);
  }
  public Statement1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Statement1(cloneProperties(),firstToken,lastToken);
  }
  public BaliGrammarRule getBaliGrammarRule() {
    return ((PropertyOne<BaliGrammarRule>)getProperty("baliGrammarRule")).getValue();
  }
}
