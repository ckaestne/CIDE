package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class AngleComplexNode extends ComplexRegex {
  public AngleComplexNode(ASTStringNode findcloseangle, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findcloseangle", findcloseangle)
    }, firstToken, lastToken);
  }
  public AngleComplexNode(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new AngleComplexNode(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindcloseangle() {
    return ((PropertyOne<ASTStringNode>)getProperty("findcloseangle")).getValue();
  }
}
