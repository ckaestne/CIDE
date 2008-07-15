package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class MemberExpressionPart2 extends MemberExpressionPart {
  public MemberExpressionPart2(Identifier identifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Identifier>("identifier", identifier)
    }, firstToken, lastToken);
  }
  public MemberExpressionPart2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new MemberExpressionPart2(cloneProperties(),firstToken,lastToken);
  }
  public Identifier getIdentifier() {
    return ((PropertyOne<Identifier>)getProperty("identifier")).getValue();
  }
}
