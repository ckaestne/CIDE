package tmp.generated_bali;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class TokenManagerDeclarations extends GenASTNode {
  public TokenManagerDeclarations(ASTStringNode _token_mgr_decls, ScanBlock scanBlock, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("_token_mgr_decls", _token_mgr_decls),
      new PropertyOne<ScanBlock>("scanBlock", scanBlock)
    }, firstToken, lastToken);
  }
  public TokenManagerDeclarations(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new TokenManagerDeclarations(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode get_token_mgr_decls() {
    return ((PropertyOne<ASTStringNode>)getProperty("_token_mgr_decls")).getValue();
  }
  public ScanBlock getScanBlock() {
    return ((PropertyOne<ScanBlock>)getProperty("scanBlock")).getValue();
  }
}
