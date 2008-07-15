package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeQualifier1 extends TypeQualifier {
  public TypeQualifier1(ASTStringNode const_kw, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("const_kw", const_kw)
    }, firstToken, lastToken);
  }
  public TypeQualifier1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeQualifier1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConst_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("const_kw")).getValue();
  }
}
