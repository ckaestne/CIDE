package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TypeSpecifier9 extends TypeSpecifier {
  public TypeSpecifier9(ASTStringNode unsigned, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("unsigned", unsigned)
    }, firstToken, lastToken);
  }
  public TypeSpecifier9(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TypeSpecifier9(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getUnsigned() {
    return ((PropertyOne<ASTStringNode>)getProperty("unsigned")).getValue();
  }
}
