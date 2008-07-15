package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumSpecifierInternal2 extends EnumSpecifierInternal {
  public EnumSpecifierInternal2(ASTStringNode identifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("identifier1", identifier1)
    }, firstToken, lastToken);
  }
  public EnumSpecifierInternal2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumSpecifierInternal2(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier1() {
    return ((PropertyOne<ASTStringNode>)getProperty("identifier1")).getValue();
  }
}
