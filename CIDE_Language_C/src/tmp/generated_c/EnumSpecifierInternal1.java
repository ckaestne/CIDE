package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumSpecifierInternal1 extends EnumSpecifierInternal {
  public EnumSpecifierInternal1(ASTStringNode identifier, EnumeratorList enumeratorList, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ASTStringNode>("identifier", identifier),
      new PropertyOne<EnumeratorList>("enumeratorList", enumeratorList)
    }, firstToken, lastToken);
  }
  public EnumSpecifierInternal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumSpecifierInternal1(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getIdentifier() {
    return ((PropertyZeroOrOne<ASTStringNode>)getProperty("identifier")).getValue();
  }
  public EnumeratorList getEnumeratorList() {
    return ((PropertyOne<EnumeratorList>)getProperty("enumeratorList")).getValue();
  }
}
