package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class exprMain1 extends exprMain {
  public exprMain1(ArrayList<expressie> expressie, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOneOrMore<expressie>("expressie", expressie)
    }, firstToken, lastToken);
  }
  public exprMain1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new exprMain1(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<expressie> getExpressie() {
    return ((PropertyOneOrMore<expressie>)getProperty("expressie")).getValue();
  }
}
