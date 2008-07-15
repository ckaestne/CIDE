package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class PrimarySuffix3 extends PrimarySuffix {
  public PrimarySuffix3(MemberSelector memberSelector, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<MemberSelector>("memberSelector", memberSelector)
    }, firstToken, lastToken);
  }
  public PrimarySuffix3(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new PrimarySuffix3(cloneProperties(),firstToken,lastToken);
  }
  public MemberSelector getMemberSelector() {
    return ((PropertyOne<MemberSelector>)getProperty("memberSelector")).getValue();
  }
}
