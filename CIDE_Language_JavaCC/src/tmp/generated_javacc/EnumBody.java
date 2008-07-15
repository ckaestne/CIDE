package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class EnumBody extends GenASTNode {
  public EnumBody(ArrayList<EnumConstant> enumConstant, EnumBodyInternal enumBodyInternal, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyList<EnumConstant>("enumConstant", enumConstant),
      new PropertyZeroOrOne<EnumBodyInternal>("enumBodyInternal", enumBodyInternal)
    }, firstToken, lastToken);
  }
  public EnumBody(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new EnumBody(cloneProperties(),firstToken,lastToken);
  }
  public ArrayList<EnumConstant> getEnumConstant() {
    return ((PropertyList<EnumConstant>)getProperty("enumConstant")).getValue();
  }
  public EnumBodyInternal getEnumBodyInternal() {
    return ((PropertyZeroOrOne<EnumBodyInternal>)getProperty("enumBodyInternal")).getValue();
  }
}
