package tmp.generated_c;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumSpecifier extends GenASTNode {
  public EnumSpecifier(ASTStringNode enum_kw, EnumSpecifierInternal enumSpecifierInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("enum_kw", enum_kw),
      new PropertyOne<EnumSpecifierInternal>("enumSpecifierInternal", enumSpecifierInternal)
    }, firstToken, lastToken);
  }
  public EnumSpecifier(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumSpecifier(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getEnum_kw() {
    return ((PropertyOne<ASTStringNode>)getProperty("enum_kw")).getValue();
  }
  public EnumSpecifierInternal getEnumSpecifierInternal() {
    return ((PropertyOne<EnumSpecifierInternal>)getProperty("enumSpecifierInternal")).getValue();
  }
}
