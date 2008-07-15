package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeQualifier2 extends TypeQualifier {
  public TypeQualifier2(ASTStringNode volatile_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("volatile_kw", volatile_kw)
    }, firstToken, lastToken);
  }
  public TypeQualifier2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeQualifier2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getVolatile_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("volatile_kw")).getValue();
  }
}
