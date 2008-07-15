package tmp.generated_cppapprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class BlockOrSingleStatement2 extends BlockOrSingleStatement {
  public BlockOrSingleStatement2(CodeUnit_InBlock codeUnit_InBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<CodeUnit_InBlock>("codeUnit_InBlock", codeUnit_InBlock)
    }, firstToken, lastToken);
  }
  public BlockOrSingleStatement2(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new BlockOrSingleStatement2(cloneProperties(),firstToken,lastToken);
  }
  public CodeUnit_InBlock getCodeUnit_InBlock() {
    return ((PropertyOne<CodeUnit_InBlock>)getProperty("codeUnit_InBlock")).getValue();
  }
}
